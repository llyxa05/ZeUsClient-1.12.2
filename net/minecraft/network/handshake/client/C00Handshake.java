package net.minecraft.network.handshake.client;

import java.io.IOException;

import my.ZeUs.Utils.HackPack;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;

public class C00Handshake implements Packet<INetHandlerHandshakeServer>
{
    private int protocolVersion;
    private String ip;
    private int port;
    private EnumConnectionState requestedState;

    public C00Handshake()
    {
    }

    public C00Handshake(final String p_i47613_1_, final int p_i47613_2_, final EnumConnectionState p_i47613_3_) {
        this.protocolVersion = 340;
        if (!HackPack.getFakeIp().isEmpty()) {
            final StringBuilder append = new StringBuilder(String.valueOf(p_i47613_1_)).append("\u0000");
            final StringBuilder append2 = append.append(HackPack.getFakeIp()).append("\u0000");
            this.ip = append2.append(HackPack.getFakeUUID().replace("-", "")).toString();
        }
        else {
            this.ip = p_i47613_1_;
        }
        this.port = p_i47613_2_;
        this.requestedState = p_i47613_3_;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.protocolVersion = buf.readVarIntFromBuffer();
        this.ip = buf.readStringFromBuffer(255);
        this.port = buf.readUnsignedShort();
        this.requestedState = EnumConnectionState.getById(buf.readVarIntFromBuffer());
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeVarIntToBuffer(this.protocolVersion);
        buf.writeString(this.ip);
        buf.writeShort(this.port);
        buf.writeVarIntToBuffer(this.requestedState.getId());
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerHandshakeServer handler)
    {
        handler.processHandshake(this);
    }

    public EnumConnectionState getRequestedState()
    {
        return this.requestedState;
    }

    public int getProtocolVersion()
    {
        return this.protocolVersion;
    }
}
